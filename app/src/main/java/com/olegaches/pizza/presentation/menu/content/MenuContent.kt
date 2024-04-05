package com.olegaches.pizza.presentation.menu.content

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.olegaches.pizza.model.Category
import com.olegaches.pizza.model.Meal
import com.olegaches.pizza.presentation.util.observeAsEvents
import com.olegaches.pizza.presentation.util.toPx
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun MenuContent(
    menuContentComponent: MenuContentComponent,
    modifier: Modifier,
) {
    val state by menuContentComponent.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    menuContentComponent.events.observeAsEvents { event ->
        when(event) {
            is MenuContentEvents.OnError -> snackbarHostState.showSnackbar(
                message = event.error.message ?: "Unknown Exception"
            )
        }
    }
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Box(Modifier.fillMaxSize()) {
            when(val currentState = state) {
                is MenuUiState.None -> {}
                MenuUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is MenuUiState.Success -> {
                    val groupedMeal = currentState.groupedMeal
                    if(groupedMeal.isNotEmpty()) {
                        MealList(groupedMeal)
                    }
                }
                is MenuUiState.Error -> {
                    val groupedMeal = currentState.groupedMeal
                    if(!groupedMeal.isNullOrEmpty()) {
                        MealList(groupedMeal)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MealList(
    list: List<Category>
) {
    val mealFlatList = remember(list) { list.flatMap { it.mealList } }
    val lazyColumnState = rememberLazyListState()
    val lazyRowState = rememberLazyListState()
    val visibleCategory by remember {
        derivedStateOf {
            val firstVisibleItemIndex = lazyColumnState.firstVisibleItemIndex
            val firstMealIndex = if(firstVisibleItemIndex == 0 || firstVisibleItemIndex == 1) {
                0
            } else {
                firstVisibleItemIndex - 1
            }
            val visibleCategory = mealFlatList[firstMealIndex].category
            visibleCategory
        }
    }
    LaunchedEffect(visibleCategory) {
        for(i in list.indices) {
            if(list[i].categoryName == visibleCategory) {
                lazyRowState.animateScrollToItem(i)
            }
        }
    }
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        state = lazyColumnState
    ) {
        item(
            key = -2
        ) {
            AdLabel()
            Spacer(modifier = Modifier.height(24.dp))
        }
        stickyHeader(
            key = -1
        ) {
            val tabsHeight = 69.dp
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(tabsHeight)
                    .background(MaterialTheme.colorScheme.background),
                state = lazyRowState,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                items(
                    items = list,
                ) { category ->
                    val scrollOffset = tabsHeight.toPx()
                    FilterChip(
                        selected = visibleCategory == category.categoryName,
                        onClick = {
                            coroutineScope.launch {
                                for(mealIndex in mealFlatList.indices) {
                                    if(mealFlatList[mealIndex].category == category.categoryName) {
                                        lazyColumnState.scrollToItem(
                                            index = mealIndex + 2,
                                            scrollOffset = -scrollOffset.toInt()
                                        )
                                        break
                                    }
                                }
                            }
                        },
                        label = {
                            Text(
                                text = category.categoryName
                            )
                        }
                    )
                }
            }
        }
        for(i in list.indices) {
            val mealList = list[i].mealList
            val headerMeal = mealList.first()
            item(
                key = headerMeal.id
            ) {
                ElevatedCard {
                    Column(Modifier.padding(start = 12.dp, end = 12.dp, top = 18.dp, bottom = 12.dp)) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 30.dp)
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(12.dp)),
                            model = headerMeal.image,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = headerMeal.name,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = headerMeal.ingredients.joinToString(),
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        AssistChip(
                            modifier = Modifier.align(Alignment.End),
                            onClick = {},
                            label = {
                                Text(
                                    text = "${headerMeal.price.toInt()} р"
                                )
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            val mealListForItems = mealList.drop(1)
            items(
                items = mealListForItems,
                key = { it.id }
            ) { meal ->
                MealItem(meal)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun MealItem(
    meal: Meal,
) {
    Row {
        AsyncImage(
            modifier = Modifier
                .size(135.dp)
                .clip(RoundedCornerShape(14.dp)),
            model = meal.image,
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.width(22.dp))
        Column {
            Text(
                text = meal.name,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = meal.ingredients.joinToString()
            )
            Spacer(modifier = Modifier.width(8.dp))
            AssistChip(
                modifier = Modifier.align(Alignment.End),
                onClick = {},
                label = {
                    Text(
                        text = "${meal.price.toInt()} р"
                    )
                }
            )
        }
    }
}

@Composable
private fun AdLabel() {
    val imageModifier = Modifier
        .height(112.dp)
        .width(300.dp)
        .clip(RoundedCornerShape(10.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        AsyncImage(
            modifier = imageModifier,
            model = "file:///android_asset/image/ad1.jpeg",
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        AsyncImage(
            modifier = imageModifier,
            model = "file:///android_asset/image/ad2.jpeg",
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}