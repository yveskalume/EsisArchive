package tech.devscast.esisarchive.ui.screen.downloaded

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FileCopy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.devscast.esisarchive.ui.theme.EsisArchiveTheme

@Composable
fun DownloadedScreen(modifier: Modifier = Modifier) {
		Scaffold(
				topBar = {
						TopAppBar(
								backgroundColor = Color.White,
								title = {
										Text(text = "Téléchargements")
								}
						)
				}
		) { contentPadding ->
				LazyColumn(
						verticalArrangement = Arrangement.spacedBy(8.dp),
						contentPadding = PaddingValues(16.dp),
						modifier = modifier.padding(contentPadding),
						content = {
								items(20) {
										Row(
												modifier = Modifier
														.clickable { }
														.fillMaxWidth()
														.padding(8.dp)
										) {
												Surface(
														shape = CircleShape,
														color = Color.Cyan,
														modifier = Modifier.size(56.dp)
												) {
														Icon(
																imageVector = Icons.Rounded.FileCopy,
																contentDescription = null,
																modifier = Modifier
																		.fillMaxSize()
																		.padding(16.dp)
														)
												}

												Spacer(modifier = Modifier.width(16.dp))

												Column(
														modifier = Modifier.wrapContentHeight(),
														verticalArrangement = Arrangement.spacedBy(
																2.dp,
																alignment = Alignment.CenterVertically
														)
												) {
														Text(
																text = "Lorem ipsum",
																style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.SemiBold)
														)

														Row(
																horizontalArrangement = Arrangement.SpaceBetween,
																verticalAlignment = Alignment.CenterVertically,
																modifier = Modifier.fillMaxWidth()
														) {
																Text(
																		text = "Musumbu Kaninda",
																		style = MaterialTheme.typography.h4
																)

																Text(
																		text = "il y a 2 jours",
																		style = MaterialTheme.typography.caption
																)
														}
												}
										}
										Divider()
								}
						}
				)
		}
}

@Preview(showBackground = true)
@Composable
fun DownloadedScreenPreview() {
		EsisArchiveTheme {
				DownloadedScreen(modifier = Modifier.fillMaxSize())
		}
}