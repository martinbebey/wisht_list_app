package com.developer.wishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.developer.wishlistapp.data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
){

    val snackMessage = remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState() //keep scaffold up to date

    if (id != 0L){
        val wish = viewModel.getAWishById(id).collectAsState(initial = Wish(0L, "", ""))
        viewModel.wishTitle = wish.value.title
        viewModel.wishDescriptionState = wish.value.description
    }
    else{
        viewModel.wishTitle = ""
        viewModel.wishDescriptionState = ""
    }

    Scaffold(
        topBar = {
            AppBarView(
                title = if(id != 0L) stringResource(id = R.string.update_wish)
                else stringResource(id = R.string.add_wish)
            ){
                navController.navigateUp()
            }
        },
        scaffoldState = scaffoldState
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(
                label = "Title",
                value = viewModel.wishTitle,
                onValueChanged = {str ->
                    viewModel.onWishTitleChanged(str)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(
                label = "Description",
                value = viewModel.wishDescriptionState,
                onValueChanged = {str ->
                    viewModel.onWishDescriptionChanged(str)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                if(viewModel.wishDescriptionState.isNotEmpty() && viewModel.wishTitle.isNotEmpty()){
                    //TODO update Wish
                    if(id != 0L){

                    }
                    //TODO Add Wish
                    else{
                        viewModel.addAWish(
                            Wish(
                                title = viewModel.wishTitle.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )

                        snackMessage.value = "Wish has been added"
                    }
                }
                else{
                    snackMessage.value = "Enter fields to create a wish"
                }

                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }
            }){
                Text(
                    text =
                        if (id != 0L) stringResource(id = R.string.update_wish)
                        else stringResource(id = R.string.add_wish),
                    style = TextStyle(fontSize = 10.sp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
){
    OutlinedTextField(
        value = value, 
        onValueChange = onValueChanged,
        label = {Text(text = label, color = Color.Black)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.purple_700),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.teal_700),
            focusedLabelColor = Color.Green,
            unfocusedLabelColor = Color.Green,
            focusedTextColor = Color.Blue,
            unfocusedTextColor = colorResource(id = R.color.black)
        )
    )
}

@Preview
@Composable
fun WishTextFieldPreview(){
    WishTextField(label = "label", value = "value") {

    }
}

