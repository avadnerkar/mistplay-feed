package com.abhi.mistplayfeed.userlist.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhi.mistplayfeed.model.Address
import com.abhi.mistplayfeed.model.Company
import com.abhi.mistplayfeed.model.Post
import com.abhi.mistplayfeed.model.User
import com.abhi.mistplayfeed.ui.theme.MFTheme
import com.abhi.mistplayfeed.userlist.UserProps

@Composable
fun UserListComponent(
    users: List<UserProps>
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(users, key = { it.user.id }) { item ->
            UserCard(item)
        }
    }
}

@Composable
fun UserCard(userProps: UserProps) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 8.dp, vertical = 100.dp)
    ) {
        Text(userProps.user.name, color = MaterialTheme.colorScheme.onTertiaryContainer)
    }
}

private fun previewUser(id: Long): UserProps = UserProps(
    user = User(
        name = "Homer Simpson",
        id = id,
        username = "av",
        address = Address(
            street = "742 Evergreen Terrace",
            suite = "1",
            city = "Springfield",
            zipcode = "90210",
            geo = null
        ),
        phone = "514-111-1111",
        website = "https://example.com",
        company = Company(
            name = "Nuclear power plant",
            catchPhrase = "Doh",
            bs = "BS?"
        ),
    ),
    highlightedPosts = listOf(
        previewPost(1),
        previewPost(2)
    ),
    hasMorePosts = true
)

private fun previewPost(id: Long): Post = Post(
    id = id,
    userId = 1,
    title = "$20 can buy you many peanuts",
    body = "Money can be exchanged for goods and services"
)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
@Composable
private fun UserListComponent_Preview() {
    MFTheme {
        Surface {
            UserListComponent(
                users = listOf(
                    previewUser(1),
                    previewUser(2)
                )
            )
        }
    }
}
