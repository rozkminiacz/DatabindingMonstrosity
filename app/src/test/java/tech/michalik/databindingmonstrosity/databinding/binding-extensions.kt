package tech.michalik.databindingmonstrosity.databinding

import android.view.View
import androidx.databinding.DataBindingComponent
import java.lang.reflect.Constructor

fun createBinding(
    bindingComponent: DataBindingComponent,
    rootView: View,
    bindings: Array<View>

): ListItemBindingImpl {
    return ListItemBindingImpl::class.java
        .let {
            it.declaredConstructors.last() as Constructor<ListItemBindingImpl>
        }.also {
            it.isAccessible = true
        }.newInstance(
            bindingComponent,
            rootView,
            bindings
        )
}