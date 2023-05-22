package uz.larslion.testapp.domain.offers

import uz.larslion.testapp.data.resource.remoteSource.response.offers.AttributeDataModel
import uz.larslion.testapp.data.resource.remoteSource.response.offers.ImageDataModel
import uz.larslion.testapp.data.resource.remoteSource.response.offers.OfferDataModel

data class OfferDomainModel(
    val brand: String,
    val category: String,
    val id: Int,
    val image: ImageDataModel,
    val merchant: String,
    val name: String,
    val attr: List<AttributeDataModel>
)

fun OfferDataModel.toDomainModel(): OfferDomainModel = OfferDomainModel(
    brand = this.brand,
    category = this.category,
    id = this.id,
    image = this.image,
    merchant = this.merchant,
    name = this.name,
    attr = this.attributes
)