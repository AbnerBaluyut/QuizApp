package com.interactive.quizapp.utils.extensions

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object Spacing {
    val extraSmall = 4.dp
    val small = 8.dp
    val medium = 16.dp
    val large = 24.dp
    val extraLarge = 32.dp
}

// Padding - all sides
fun Modifier.paddingExtraSmall() = this.padding(Spacing.extraSmall)
fun Modifier.paddingSmall() = this.padding(Spacing.small)
fun Modifier.paddingMedium() = this.padding(Spacing.medium)
fun Modifier.paddingLarge() = this.padding(Spacing.large)
fun Modifier.paddingExtraLarge() = this.padding(Spacing.extraLarge)

// Padding - horizontal only
fun Modifier.paddingHorizontalExtraSmall() = this.padding(horizontal = Spacing.extraSmall)
fun Modifier.paddingHorizontalSmall() = this.padding(horizontal = Spacing.small)
fun Modifier.paddingHorizontalMedium() = this.padding(horizontal = Spacing.medium)
fun Modifier.paddingHorizontalLarge() = this.padding(horizontal = Spacing.large)
fun Modifier.paddingHorizontalExtraLarge() = this.padding(horizontal = Spacing.extraLarge)

// Padding - vertical only
fun Modifier.paddingVerticalExtraSmall() = this.padding(vertical = Spacing.extraSmall)
fun Modifier.paddingVerticalSmall() = this.padding(vertical = Spacing.small)
fun Modifier.paddingVerticalMedium() = this.padding(vertical = Spacing.medium)
fun Modifier.paddingVerticalLarge() = this.padding(vertical = Spacing.large)
fun Modifier.paddingVerticalExtraLarge() = this.padding(vertical = Spacing.extraLarge)