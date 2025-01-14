package com.example.binlist.bininfo.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

// Компонент для ввода BIN
@Composable
fun BinInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    // Хранение состояния для управления текстом и курсором
    val textFieldValue = remember { mutableStateOf(TextFieldValue(value)) }

    OutlinedTextField(
        value = textFieldValue.value,
        onValueChange = { newValue ->
            // Убираем пробелы и оставляем только цифры
            val digitsOnly = newValue.text.filter { it.isDigit() }
            // Ограничиваем ввод до 16 цифр
            val limitedDigits = digitsOnly.take(8)
            // Форматируем текст в группы по 4 цифры
            val formattedText = limitedDigits.chunked(4).joinToString(" ")
            // Вычисляем позицию курсора с учетом форматирования
            val cursorPosition = calculateCursorPosition(newValue.selection.start, limitedDigits)
            // Обновляем состояние TextFieldValue
            textFieldValue.value = TextFieldValue(
                text = formattedText,
                selection = TextRange(cursorPosition) // Устанавливаем курсор в конец
            )
            onValueChange(formattedText.replace(" ", "")) // Передаем отформатированный текст и удаляем пробелы перед отправкой
        },
        shape = RoundedCornerShape(25.dp),
        label = {
            Text(
                text = label,
                color = Color.Black
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                color = Color.LightGray
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color.Black,
            focusedIndicatorColor = Color.Black,
            cursorColor = Color.Black
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

// Функция для вычисления корректной позиции курсора после форматирования
fun calculateCursorPosition(rawCursorPosition: Int, digits: String): Int {
    // Считаем количество пробелов, которые будут добавлены до текущей позиции курсора
    val spaceCount = rawCursorPosition / 4
    // Сдвигаем курсор с учетом пробелов
    return rawCursorPosition + spaceCount
}
