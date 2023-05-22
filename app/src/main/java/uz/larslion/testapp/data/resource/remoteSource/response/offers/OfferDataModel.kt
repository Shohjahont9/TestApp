package uz.larslion.testapp.data.resource.remoteSource.response.offers

data class OfferDataModel(
    val attributes: List<AttributeDataModel>,
    val brand: String,
    val category: String,
    val id: Int,
    val image: ImageDataModel,
    val merchant: String,
    val name: String
)