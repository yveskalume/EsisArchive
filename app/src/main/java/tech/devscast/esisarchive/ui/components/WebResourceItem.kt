package tech.devscast.esisarchive.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowRightAlt
import androidx.compose.material.icons.rounded.FilePresent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.devscast.esisarchive.ui.theme.EsisArchiveTheme
import tech.devscast.esisarchive.ui.theme.Gray002

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WebResourceItem(modifier: Modifier = Modifier, onClick: () -> Unit) {

		Row(
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.SpaceBetween,
				modifier = modifier
						.fillMaxWidth()
						.height(IntrinsicSize.Min)
						.clip(RoundedCornerShape(8.dp))
						.background(Gray002)
						.clickable(onClick = onClick)
						.padding(8.dp)
		) {
				Row {
						Surface(
								shape = RoundedCornerShape(8.dp),
								color = Color.Transparent,
								modifier = Modifier
										.size(100.dp)
										.padding(4.dp)
						) {
								Image(
										imageVector = Icons.Rounded.FilePresent,
										contentDescription = null,
										modifier = Modifier
												.fillMaxSize(),
								)
						}

						Spacer(modifier = Modifier.width(8.dp))

						Column(
								modifier = Modifier
										.fillMaxHeight()
										.padding(vertical = 6.dp),
						) {
								Text(
										text = "Openclassroom",
										style = MaterialTheme.typography.h4,
										fontWeight = FontWeight.W400
								)
								Spacer(modifier = Modifier.height(4.dp))
								Text(
										text = "Lorem ipsum",
										style = MaterialTheme.typography.caption,
										fontWeight = FontWeight.W400
								)
								Spacer(modifier = Modifier.height(8.dp))
								Row {
										Text(text = "#Programmation", style = MaterialTheme.typography.caption)
								}
						}
				}

				Icon(
						imageVector = Icons.Rounded.ArrowRightAlt,
						contentDescription = null,
						modifier = Modifier.padding(end = 8.dp)
				)
		}
}

@Preview(showBackground = true)
@Composable
fun WebResourceItemPreview() {
		EsisArchiveTheme {
				WebResourceItem(
						onClick = {}
				)
		}
}