package tech.michalik.databindingmonstrosity.databinding

import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingComponent
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.kotlintest.specs.StringSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import tech.michalik.databindingmonstrosity.ImageBindings
import tech.michalik.databindingmonstrosity.ImageLoader
import tech.michalik.databindingmonstrosity.ListItemDisplayable

class ListItemBindingImplTest : StringSpec({

    mockkStatic(Looper::class)
    every { Looper.myLooper() } returns mockk<Looper>()

    "it should set avatar"{
        val displayable = ListItemDisplayable(
            text = "This is the content",
            avatar = "http://some-kind-of-cdn.net/asd.jpg",
            canEdit = false
        )

        val avatarImageView = mock<ImageView>()

        val bindings = arrayOf(
            mock<ConstraintLayout>(),
            avatarImageView,
            mock<TextView>(),
            mock<ImageView>()
        )

        val imageLoader = mock<ImageLoader>()

        val binding: ListItemBindingImpl = createListItemBinding(
            bindings, imageLoader
        )

        binding.viewItem = displayable
        binding.executeBindings()

        verify(imageLoader).loadImageFromUrl(avatarImageView, displayable.avatar)
    }

    "it should set text"{
        val displayable = ListItemDisplayable(
            text = "This is the content",
            avatar = "http://some-kind-of-cdn.net/asd.jpg",
            canEdit = false
        )

        val textView = mock<TextView>()

        val bindings = arrayOf(
            mock<ConstraintLayout>(),
            mock<ImageView>(),
            textView,
            mock<ImageView>()
        )

        val imageLoader = mock<ImageLoader>()

        val binding: ListItemBindingImpl = createListItemBinding(
            bindings, imageLoader
        )

        binding.viewItem = displayable
        binding.executeBindings()

        verify(textView).setText(displayable.text)
    }

    "when can edit then show edit icon"{
        val displayable = ListItemDisplayable(
            text = "This is the content",
            avatar = "http://some-kind-of-cdn.net/asd.jpg",
            canEdit = true
        )

        val canEditView = mock<ImageView>()

        val bindings = arrayOf(
            mock<ConstraintLayout>(),
            mock<ImageView>(),
            mock<TextView>(),
            canEditView
        )

        val imageLoader = mock<ImageLoader>()

        val binding: ListItemBindingImpl = createListItemBinding(
            bindings, imageLoader
        )

        binding.viewItem = displayable
        binding.executeBindings()

        verify(canEditView).setVisibility(View.VISIBLE)
    }

    "when can edit then hide edit icon"{
        val displayable = ListItemDisplayable(
            text = "This is the content",
            avatar = "http://some-kind-of-cdn.net/asd.jpg",
            canEdit = false
        )

        val canEditView = mock<ImageView>()

        val bindings = arrayOf(
            mock<ConstraintLayout>(),
            mock<ImageView>(),
            mock<TextView>(),
            canEditView
        )

        val imageLoader = mock<ImageLoader>()

        val binding: ListItemBindingImpl = createListItemBinding(
            bindings, imageLoader
        )

        binding.viewItem = displayable
        binding.executeBindings()

        verify(canEditView).setVisibility(View.GONE)
    }
})

private fun createListItemBinding(
    bindings: Array<View>,
    imageLoader: ImageLoader
) =
    createBinding(
        bindingComponent = object : DataBindingComponent {
            override fun getImageBindings(): ImageBindings {
                return ImageBindings(
                    imageLoader = imageLoader
                )
            }
        },
        rootView = mock(),
        bindings = bindings
    )