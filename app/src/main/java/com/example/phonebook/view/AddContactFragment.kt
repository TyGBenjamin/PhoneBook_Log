package com.example.phonebook.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.phonebook.R
import com.example.phonebook.model.data.local.Address
import com.example.phonebook.model.data.local.Contact
import com.example.phonebook.ui.theme.PhoneBookTheme
import com.example.phonebook.viewmodel.AddContactViewModel
import com.example.phonebook.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddContactFragment : Fragment() {
    private val dashboardViewModel by activityViewModels<DashboardViewModel>()
    private val contactViewModel by activityViewModels<AddContactViewModel>()
    val cellNum by lazy { arguments?.getString("cell") }
    val workNum by lazy { arguments?.getString("work") }
    val homeNum by lazy { arguments?.getString("home") }
    val workEmail by lazy { arguments?.getString("workEmail") }
    val altEmail by lazy { arguments?.getString("altEmail") }
    val streetAddress by lazy { arguments?.getString("street") }
    val city by lazy { arguments?.getString("city") }
    val state by lazy { arguments?.getString("state") }
    val zipCode by lazy { arguments?.getString("zipcode") }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireActivity()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                PhoneBookTheme {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                    ) {
                        Column(
                            Modifier
                                .padding(24.dp)
                                .wrapContentSize()
                                .background(Color.Black),
                            verticalArrangement = Arrangement.spacedBy(
                                13.dp,
                                alignment = Alignment.Bottom
                            ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            var value by remember {
                                mutableStateOf("")
                            }

                            var valueTwo by remember {
                                mutableStateOf("")
                            }

                            IconOne()
                            TextField(value = value, onValueChange = { value = it },
                                label = { Label("enter first Name") })
                            TextField(value = valueTwo, onValueChange = { valueTwo = it },
                                label = { Label("enter last Name") })
                            val addedContact = Contact(
                                firstName = value,
                                lastName = valueTwo,
                                phone = listOf(cellNum ?: "", workNum ?: "", homeNum ?: ""),
                                email = listOf(workEmail ?: "", altEmail ?: ""),
                                address = Address(
                                    streetAddress ?: "",
                                    city ?: "",
                                    state ?: "",
                                    zipCode ?: ""
                                )
                            )
                          MyButton(text = "Save Contact Info", action = { dashboardViewModel.addContactNew(addedContact) })

                            println("NEWLY ADDED CONTACT IS $addedContact")
                            DividerOne()
                            Row(verticalAlignment = Alignment.CenterVertically) {
                            }
                        }
                        Column {
                            Text(
                                text = "Click Icon Below To Add More Details",
                                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(start = 70.dp, bottom = 10.dp)
                            )
                            Row(modifier = Modifier.padding(start = 115.dp)) {
                                IconLabels(resource = R.drawable.ic_baseline_perm_phone_msg_24,
                                    navigate = { findNavController().navigate(R.id.addPhoneFragment) })
                                IconLabels(resource = R.drawable.ic_baseline_email_24,
                                    navigate = { findNavController().navigate(R.id.addEmailFragment) })
                                IconLabels(resource = R.drawable.ic_baseline_house_24,
                                    navigate = { findNavController().navigate(R.id.addAddressFragment) })
                            }
                            DividerOne()

                            Row() {
                                MyButton(
                                    text = stringResource(R.string.homeList),
                                    action = { findNavController().navigate(R.id.dashboardFragment) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContact(
) {
    Column(
        Modifier
            .padding(24.dp)
            .wrapContentSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(13.dp, alignment = Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var value by remember {
            mutableStateOf("")
        }

        var valueTwo by remember {
            mutableStateOf("")
        }

        IconOne()
        TextField(value = value, onValueChange = { value = it },
            label = { Label("enter first Name") })
        TextField(value = valueTwo, onValueChange = { valueTwo = it },
            label = { Label("enter last Name") })
        DividerOne()
        Row(verticalAlignment = Alignment.CenterVertically) {
        }
    }
}

@Composable
fun MyButton(text: String, action: () -> Unit) {
    Button(
        onClick = {
            action()
        }, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
    ) {
        Text(
            text = text,
            fontSize = 20.sp
        )
    }
}

@Composable
fun AddContactHere() {
    Box(
        modifier = Modifier.padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = "")
        }
    }
}

/**
 *
 */
@Composable
fun DividerOne() {
    Divider(
        color = Color.White.copy(alpha = 0.3f),
        thickness = 1.dp,
        modifier = Modifier.padding(top = 48.dp)
    )
}

@Composable
fun IconOne() {
    Box(
        modifier = Modifier.padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.person_add),
            null,
            Modifier.size(200.dp),
            tint = Color.White
        )
    }
}

@Composable
fun Label(typeInput: String = "Show Result") {
    Text(
        text = typeInput,
        fontSize = 9.sp
    )
}

@Composable
fun Label2() {
    Text(
        text = "input number",
        fontSize = 8.sp
    )
}

@Composable
fun IconLabels(resource: Int, navigate: () -> Unit) {
    Box(
        modifier = Modifier.padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = resource),
            null,
            Modifier
                .size(50.dp)
                .clickable { navigate() },
            tint = Color.White
        )
    }
}


