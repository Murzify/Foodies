package com.murzify.gosporttest

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

enum class BottomBarEnum(
    @StringRes val tabName: Int,
    @DrawableRes val iconId: Int
) {
    MENU(R.string.menu_tab, R.drawable.ic_menu),
    PROFILE(R.string.profile_tab, R.drawable.ic_union),
    CART(R.string.cart_tab, R.drawable.ic_cart)
}