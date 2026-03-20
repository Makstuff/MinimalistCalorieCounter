package com.makstuff.minimalistcaloriecounter.ui.reused

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.makstuff.minimalistcaloriecounter.ui.theme.AppTheme

@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { 
            Text(
                text = label,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            ) 
        },
        placeholder = { 
            Text(
                text = placeholder,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            ) 
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun TextFieldPreview() {
    AppTheme {
        TextField(
            value = "Sample Text",
            onValueChange = {},
            label = "Label",
            placeholder = "Placeholder",
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default
        )
    }
}
