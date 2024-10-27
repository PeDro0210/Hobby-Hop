package com.pedro0210.hobbylobby.Screens.Profile.ViewModel

import com.pedro0210.hobbylobby.presentation.model.SocialMedia

interface ProfileEvent {
    data object onLogoutClick: ProfileEvent
    data object onDoneEditing: ProfileEvent
    data class onDoneAdding(val socialMedia: SocialMedia): ProfileEvent
}