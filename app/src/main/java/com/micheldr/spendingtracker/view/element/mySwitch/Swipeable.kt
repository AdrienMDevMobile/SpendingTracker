package com.micheldr.spendingtracker.view.element.mySwitch

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableDefaults
import androidx.compose.material.SwipeableState
import androidx.compose.runtime.*

/**
 * Create and [remember] a [SwipeableState] which is kept in sync with another state, i.e.:
 *  1. Whenever the [value] changes, the [SwipeableState] will be animated to that new value.
 *  2. Whenever the value of the [SwipeableState] changes (e.g. after a swipe), the owner of the
 *  [value] will be notified to update their state to the new value of the [SwipeableState] by
 *  invoking [onValueChange]. If the owner does not update their state to the provided value for
 *  some reason, then the [SwipeableState] will perform a rollback to the previous, correct value.
 */
@Composable
@ExperimentalMaterialApi
internal fun <T : Any> rememberSwipeableStateFor(
    value: T,
    onValueChange: (T) -> Unit,
    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec
): SwipeableState<T> {
    val swipeableState = remember {
        SwipeableState(
            initialValue = value,
            animationSpec = animationSpec,
            confirmStateChange = { true }
        )
    }
    val forceAnimationCheck = remember { mutableStateOf(false) }
    LaunchedEffect(value, forceAnimationCheck.value) {
        if (value != swipeableState.currentValue) {
            swipeableState.animateTo(value)
        }
    }
    DisposableEffect(swipeableState.currentValue) {
        if (value != swipeableState.currentValue) {
            onValueChange(swipeableState.currentValue)
            forceAnimationCheck.value = !forceAnimationCheck.value
        }
        onDispose { }
    }
    return swipeableState
}