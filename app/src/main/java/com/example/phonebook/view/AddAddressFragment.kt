package com.example.phonebook.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.phonebook.R
import com.example.phonebook.ui.theme.PhoneBookTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddAddressFragment: Fragment() {


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
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                    ) {
                        AddAddress(fragment = this@AddAddressFragment)

                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAddress(
    fragment: AddAddressFragment
) {
    Column(
        Modifier
            .padding(24.dp)
            .wrapContentSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(13.dp, alignment = Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var streetAddress by remember {
            mutableStateOf("")
        }

        var city by remember {
            mutableStateOf("")
        }

        var state by remember {
            mutableStateOf("")
        }

        var zipCode by remember {
            mutableStateOf("")
        }


        IconOne()
        TextField(value = streetAddress, onValueChange = { streetAddress = it },
            label = { Label("enter street Address") })
        TextField(value = city, onValueChange = { city = it },
            label = { Label("enter city #") })
        TextField(value = state, onValueChange = { state = it },
            label = { Label("enter State") })
        TextField(value = zipCode, onValueChange = { zipCode = it },
            label = { Label("enter zipcode") })
        DividerOne()
        Row(verticalAlignment = Alignment.CenterVertically) {
        }
        DividerOne()
        Button(
            onClick = {
                val args: Bundle = bundleOf(
                    "street" to streetAddress,
                    "city" to city,
                    "state" to state,
                    "zipcode" to zipCode.toString(),
                )
                NavHostFragment.findNavController(fragment = fragment)
                    .navigate(R.id.addContactFragment, args)

            }, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Text(
                text = "save Address",
                fontSize = 20.sp,
                color = Color.Black
            )
        }
    }
}
