package com.example.phonebook.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.phonebook.R
import com.example.phonebook.model.data.local.Contact
import com.example.phonebook.ui.theme.PhoneBookTheme
import com.example.phonebook.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Dashboard fragment.
 *
 * @constructor Create empty Dashboard fragment
 */
@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private val dashViewModel by activityViewModels<DashboardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireActivity()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                PhoneBookTheme {
                    dashViewModel.getContacts()
                    val contacts = dashViewModel.contactList.collectAsState().value
                    Column(

                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                    ) {
                        Text(text = "Hello", color = Color.White)
                        HomeScreen(contacts = contacts)
                        Button(onClick = { findNavController().navigate(R.id.addContactFragment) }) {
                            Text(
                                text = context.getString(R.string.addContactButton),
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    contacts: List<Contact>
) {
    LazyColumn(state = rememberLazyListState(), modifier = Modifier.padding(5.dp)) {
        items(items = contacts) { contact ->
            ContactRow(
                contact = contact,
            )
        }
    }
}

@Composable
fun ImageThumbnail() {
    Image(
        modifier = Modifier.clip(CircleShape),
        painter = painterResource(id = R.drawable.baseline_person),
        contentDescription = "user place holder"
    )
}

@Composable
fun ContactRow(contact: Contact) {
    Column() {
        val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }
        Row(modifier = Modifier.clickable {
            setSnackBarState(!snackbarVisibleState)
        }) {
            ImageThumbnail()
            Text(
                text = "${contact.firstName} ${contact.lastName}",
                color = Color.White,
                fontSize = 25.sp
            )
            Text(text = stringResource(R.string.clickFor))
        }
        if (snackbarVisibleState) {
            Snackbar(
                action = {
                    Button(onClick = {}) {
                        Text(stringResource(R.string.editcontatcHEre))
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Column() {
                    Text(text = "Email: ${contact.email.first()}")
                    Text(text = "Cell Number: ${contact.phone.first()}")
                    Text(text = "Alt Number: ${contact.phone.get(1)}")
                    Text(text = "Address: ${contact.address.streetAddress}")
                    Text(text = "city: ${contact.address.city}")
                    Text(text = "state: ${contact.address.state}")
                    Text(text = "zip: ${contact.address.zipcode}")
                }
            }
        }
    }
}
