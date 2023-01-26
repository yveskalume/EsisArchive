package tech.devscast.esisarchive.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.FilePresent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.devscast.esisarchive.data.entity.Course
import tech.devscast.esisarchive.data.entity.Promotion
import tech.devscast.esisarchive.ui.theme.EsisArchiveTheme
import tech.devscast.esisarchive.ui.theme.Gray002

@Composable
fun CourseItem(modifier: Modifier = Modifier, course: Course, onClick: () -> Unit) {

		Row(
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.SpaceBetween,
				modifier = modifier
						.fillMaxWidth()
						.clip(RoundedCornerShape(8.dp))
						.background(Gray002)
						.clickable(onClick = onClick)
						.padding(8.dp)
		) {
				Row(verticalAlignment = Alignment.CenterVertically) {
						Surface(
								shape = RoundedCornerShape(8.dp),
								color = Color.Transparent,
								modifier = Modifier
										.size(54.dp)
										.padding(4.dp)
						) {
								Icon(
										imageVector = Icons.Rounded.FilePresent,
										contentDescription = course.title,
										modifier = Modifier
												.padding(8.dp)
												.fillMaxSize(),
								)
						}

						Spacer(modifier = Modifier.width(8.dp))

						Column(modifier = Modifier.wrapContentHeight()) {
								Text(
										text = course.title,
										style = MaterialTheme.typography.h3,
										fontWeight = FontWeight.W400
								)

								Text(
										text = course.author,
										style = MaterialTheme.typography.caption,
										fontWeight = FontWeight.W400
								)
						}
				}

				Icon(imageVector = Icons.Rounded.ArrowForwardIos, contentDescription = null)
		}
}

@Preview(showBackground = true)
@Composable
fun CourseItemPreview() {
		EsisArchiveTheme {
				CourseItem(
						course = Course(
								title = "Lorem ipsum",
								author = "Musumbu kaninda",
								promotion = Promotion.L1
						), onClick = {})
		}
}