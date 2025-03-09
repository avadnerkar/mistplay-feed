package com.abhi.mistplayfeed.userdetail.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhi.mistplayfeed.ui.component.NameHeader
import com.abhi.mistplayfeed.ui.component.Post
import com.abhi.mistplayfeed.ui.component.PostProps
import com.abhi.mistplayfeed.ui.theme.MFTheme
import com.abhi.mistplayfeed.userdetail.UserDetailState

@Composable
fun UserDetailComponent(state: UserDetailState.Loaded) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            NameHeader(state.name)
        }

        state.companyProps?.let {
            item {
                CompanySection(it)
            }
        }

        state.posts.takeUnless { it.isEmpty() }?.let { posts ->
            items(posts, key = { it.id }) { item ->
                Post(postProps = item)
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
@Composable
private fun UserDetailComponent_Preview() {
    MFTheme {
        Surface {
            UserDetailComponent(
                state = UserDetailState.Loaded(
                    name = "Frank Grimes",
                    companyProps = CompanyProps(
                        companyName = "Springfield Power Plant",
                        catchPhrase = "I don't need safety gloves because I'm Homer Simps..."
                    ),
                    posts = listOf(
                        PostProps(
                            id = 1L,
                            title = "Homer's enemy",
                            body = "I live above a bowling alley and below another bowling alley"
                        )
                    )
                )
            )
        }
    }
}