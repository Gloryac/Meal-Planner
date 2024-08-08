package com.example.gjlunchbox.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gjlunchbox.R

@Composable
fun HomeScreen(
    user: HomeModel.User,
    inspirations: List<HomeModel.Inspiration>,
    onDietaryOptionSelected: (String) -> Unit,
    onAddFriendClicked: () -> Unit,
    onInspirationClicked: (HomeModel.Inspiration) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        UserProfile(user = user)
        TopSection()
        FoodOptions(onDietaryOptionSelected = onDietaryOptionSelected)
        AddFriends(onAddFriendClicked = onAddFriendClicked)
        InspirationList(inspirations = inspirations, onInspirationClicked = onInspirationClicked)
    }
}

@Composable
fun UserProfile(user: HomeModel.User) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.avatar), // Replace with actual image resource
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Welcome!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = user.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
        Button(
            onClick = { /* Handle call to action */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Start Your Journey")
        }
    }
}

@Composable
fun TopSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = stringResource(id = R.string.home1_text))
        Image(
            painter = painterResource(id = R.drawable.homescreen1),
            contentDescription = stringResource(id = R.string.description_homeScreen1),
            modifier = Modifier.size(100.dp)
        )
    }
}

@Composable
fun FoodOptions(onDietaryOptionSelected: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        HomeUtils.Constants.dietaryOptions.forEach { option ->
            Button(onClick = { onDietaryOptionSelected(option) }) {
                Text(text = option)
            }
        }
    }
}

@Composable
fun AddFriends(onAddFriendClicked: () -> Unit) {
    Button(
        onClick = onAddFriendClicked,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Add Friends")
    }
}

@Composable
fun InspirationList(
    inspirations: List<HomeModel.Inspiration>,
    onInspirationClicked: (HomeModel.Inspiration) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(inspirations.size) { index ->
            InspirationCard(
                inspiration = inspirations[index],
                onClick = { onInspirationClicked(inspirations[index]) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InspirationCard(inspiration: HomeModel.Inspiration, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp)),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.homescreen1), // Replace with actual image resource
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = inspiration.title,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        user = HomeModel.User(
            name = "Gloria Chebet",
            profilePicture = R.drawable.avatar // Replace with actual drawable resource
        ),
        inspirations = listOf(
            HomeModel.Inspiration(imageResource = R.drawable.homescreen1, title = "Inspiration 1"),
            HomeModel.Inspiration(imageResource = R.drawable.homescreen1, title = "Inspiration 2")
        ),
        onDietaryOptionSelected = {},
        onAddFriendClicked = {},
        onInspirationClicked = {}
    )
}