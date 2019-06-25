package org.bavand.ui.search;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by shadman on 12/26/2016.
 */

public class SuggestionProvider extends SearchRecentSuggestionsProvider {
    public final static String AU = "org.bavand.ui.search.SuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public SuggestionProvider() {
        setupSuggestions(AU, MODE);
    }
}
