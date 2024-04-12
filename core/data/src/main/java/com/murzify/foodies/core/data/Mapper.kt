package com.murzify.foodies.core.data

import com.murzify.foodies.core.domain.model.Category
import com.murzify.foodies.core.domain.model.Product
import com.murzify.foodies.core.domain.model.Tag
import com.murzify.foodies.core.network.model.CategoryDto
import com.murzify.foodies.core.network.model.ProductDto
import com.murzify.foodies.core.network.model.TagDto

internal fun ProductDto.toProduct(tags: List<TagDto>) = Product(
    id,
    categoryId,
    name,
    description,
    image,
    priceCurrent,
    measure,
    measureUnit,
    energy,
    proteins,
    fats,
    carbohydrates,
    tagIds.toTags(tags)
)

internal fun List<Int>.toTags(tags: List<TagDto>) = mapNotNull { tagId ->
    tags.firstOrNull { tag -> tag.id == tagId }?.let { tagDto ->
        Tag(
            tagDto.id,
            tagDto.name
        )
    }
}

internal fun List<ProductDto>.toProducts(tags: List<TagDto>) = map { productDto ->
    productDto.toProduct(tags)
}

internal fun List<CategoryDto>.toCategories() = map { categoryDto ->
    categoryDto.toCategory()
}

private fun CategoryDto.toCategory() = Category(id, name)
