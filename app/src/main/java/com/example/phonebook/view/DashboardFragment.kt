package com.example.phonebook.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.phonebook.R
import com.example.phonebook.model.data.local.Contact
import com.example.phonebook.ui.theme.PhoneBookTheme
import com.example.phonebook.viewmodel.AddContactViewModel
import com.example.phonebook.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private val dashViewModel by activityViewModels<DashboardViewModel>()
    private val contactViewModel by activityViewModels<AddContactViewModel>()



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
//                    val currentContact = contactViewModel.contactListO.collectAsState().value
//                    dashViewModel.addContactNew(currentContact)
//                    val job = dashViewModel.getContacts()
                    val contacts = dashViewModel.contactList.collectAsState().value
                    Column(

                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                    ) {
                        println("CONTACTS ARE $contacts")
//                        println("CURRENT FOR TESTING $currentContact")
                        Text(text = "Hello", color = Color.White)
                        HomeScreen(navigate = { { /*TODO*/ } }, contacts = contacts)
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
    navigate: () -> Unit,
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
fun CircleShapeDemo() {
    ExampleBox(shape = CircleShape)
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
    Row {
        ImageThumbnail()
        Text(text = "${contact.firstName} ${contact.lastName}")

    }
}

@Composable
fun ExampleBox(shape: Shape) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(shape)
        )
    }
}



