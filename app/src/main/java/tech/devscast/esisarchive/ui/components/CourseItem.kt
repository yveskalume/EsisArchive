package tech.devscast.esisarchive.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import tech.devscast.esisarchive.R
import tech.devscast.esisarchive.data.entity.Course

@Composable
fun CourseItem(modifier: Modifier = Modifier,course: Course) {
		Column(
				modifier = modifier
		) {
				Surface(
						shape = RoundedCornerShape(8.dp),
						modifier = Modifier
								.height(200.dp)
								.fillMaxWidth()
				) {
						Image(
								painter = painterResource(id = R.drawable.ic_launcher_background),
								contentScale = ContentScale.Crop,
								contentDescription = null
						)
				}

				Text(
						text = course.title,
						style = MaterialTheme.typography.h4,
						color = Color.Black,
						modifier = Modifier.fillMaxWidth()
				)

				Text(
						text = course.author,
						style = MaterialTheme.typography.caption,
						modifier = Modifier.fillMaxWidth()
				)
		}
}