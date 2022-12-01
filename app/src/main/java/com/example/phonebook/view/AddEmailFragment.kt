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
class AddEmailFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // requireActivity()
        return ComposeView(requireActivity()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                PhoneBookTheme {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                    ) {
                        AddEmail(fragment = this@AddEmailFragment)

                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEmail(
    fragment: AddEmailFragment
) {
    Column(
        Modifier
            .padding(24.dp)
            .wrapContentSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(13.dp, alignment = Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var workEmail by remember {
            mutableStateOf("")
        }

        var altEmail by remember {
            mutableStateOf("")
        }

        IconOne()
        TextField(value = workEmail, onValueChange = { workEmail = it },
            label = { Label("enter @Work Email") })
        TextField(value = altEmail, onValueChange = { altEmail = it },
            label = { Label("enter @alt Email #") })
        DividerOne()
        Row(verticalAlignment = Alignment.CenterVertically) {
        }
        DividerOne()
        Button(
            onClick = {
                val args: Bundle = bundleOf(
                    "workEmail" to workEmail,
                    "altEmail" to altEmail,
                )
                NavHostFragment.findNavController(fragment = fragment)
                    .navigate(R.id.addContactFragment, args)

            }, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(
                text = "save Email(s)",
                fontSize = 20.sp
            )
        }
    }
}
