package com.example.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.LinkedList;

/**
 * Created by nadeem on 20-06-2015.
 */
public class GroupedInputFormatWatcher implements TextWatcher {

    private static final char SPACE_CHAR = ' ';
    private static final String SPACE_STRING = String.valueOf(SPACE_CHAR);
    private static final int GROUPSIZE = 4;

    /**
     * Breakdown of this regexp:
     * ^             - Start of the string
     * (\\d{4}\\s)*  - A group of four digits, followed by a whitespace, e.g. "1234 ". Zero or more times.
     * \\d{0,4}      - Up to four (optional) digits.
     * (?<!\\s)$     - End of the string, but NOT with a whitespace just before it.
     *
     * Example of matching strings:
     *  - "2304 52"
     *  - "2304"
     *  - ""
     */
    private final String regexp = "^(\\d{4}\\s)*\\d{0,4}(?<!\\s)$";
    private boolean isUpdating = false;

    private final EditText editText;

    public GroupedInputFormatWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String originalString = s.toString();

        // Check if we are already updating, to avoid infinite loop.
        // Also check if the string is already in a valid format.
        if (isUpdating || originalString.matches(regexp)) {
            return;
        }

        // Set flag to indicate that we are updating the Editable.
        isUpdating = true;

        // First all whitespaces must be removed. Find the index of all whitespace.
        LinkedList<Integer> spaceIndices = new LinkedList<Integer>();
        for (int index = originalString.indexOf(SPACE_CHAR); index >= 0; index = originalString.indexOf(SPACE_CHAR, index + 1)) {
            spaceIndices.offerLast(index);
        }

        // Delete the whitespace, starting from the end of the string and working towards the beginning.
        Integer spaceIndex = null;
        while (!spaceIndices.isEmpty()) {
            spaceIndex = spaceIndices.removeLast();
            s.delete(spaceIndex, spaceIndex + 1);
        }

        // Loop through the string again and add whitespaces in the correct positions
        for(int i = 0; ((i + 1) * GROUPSIZE + i) < s.length(); i++) {
            s.insert((i + 1) * GROUPSIZE + i, SPACE_STRING);
        }

        // Finally check that the cursor is not placed before a whitespace.
        // This will happen if, for example, the user deleted the digit '5' in
        // the string: "1234 567".
        // If it is, move it back one step; otherwise it will be impossible to delete
        // further numbers.
        int cursorPos = editText.getSelectionStart();
        if (cursorPos > 0 && s.charAt(cursorPos - 1) == SPACE_CHAR) {
            editText.setSelection(cursorPos - 1);
        }

        isUpdating = false;
    }
}