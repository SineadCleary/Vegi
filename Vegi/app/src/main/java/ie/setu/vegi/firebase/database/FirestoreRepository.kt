package ie.setu.vegi.firebase.database

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import ie.setu.vegi.data.rules.Constants.PRODUCT_COLLECTION
import ie.setu.vegi.data.rules.Constants.USER_EMAIL
import ie.setu.vegi.firebase.services.AuthService
import ie.setu.vegi.firebase.services.FirestoreService
import ie.setu.vegi.firebase.services.Product
import ie.setu.vegi.firebase.services.Products
import jakarta.inject.Inject
import kotlinx.coroutines.tasks.await
import java.util.Date

class FirestoreRepository
@Inject constructor(private val auth: AuthService,
                    private val firestore: FirebaseFirestore
) : FirestoreService {

    override suspend fun getAll(email: String): Products {

        return firestore.collection(PRODUCT_COLLECTION)
            .whereEqualTo(USER_EMAIL, email)
            .dataObjects()
    }

    override suspend fun get(email: String,
                             productId: String): Product? {
        return firestore.collection(PRODUCT_COLLECTION)
            .document(productId).get().await().toObject()
    }

    override suspend fun getByBarcode(email: String, barcode: String): Product? {
        val result = firestore.collection(PRODUCT_COLLECTION)
            .whereEqualTo("email", email)
            .whereEqualTo("barcode", barcode)
            .get()
            .await()
        return result.documents.firstOrNull()?.toObject(Product::class.java)
    }

    override suspend fun insert(email: String,
                                product: Product)
    {
        val productWithEmail = product.copy(email = email)

        firestore.collection(PRODUCT_COLLECTION)
            .document("${email}_${product.barcode}")
            .set(productWithEmail)
            .await()

    }

    override suspend fun update(email: String,
                                product: Product)
    {
        val productWithModifiedDate =
            product.copy(dateAdded = Date())

        firestore.collection(PRODUCT_COLLECTION)
            .document("${email}_${product.barcode}")
            .set(productWithModifiedDate).await()
    }

    override suspend fun delete(email: String,
                                productId: String)
    {
        firestore.collection(PRODUCT_COLLECTION)
            .document(productId)
            .delete().await()
    }
}
