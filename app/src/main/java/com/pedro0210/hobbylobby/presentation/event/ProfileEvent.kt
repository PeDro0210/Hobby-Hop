package com.pedro0210.hobbylobby.presentation.event

import com.pedro0210.hobbylobby.presentation.model.SocialMedia

interface ProfileEvent {
    data object onLogoutClick: ProfileEvent
    data object onDoneEditing: ProfileEvent
    data class onDoneAdding(val socialMedia: SocialMedia): ProfileEvent
    data class onLoadUser(val ID: Int): ProfileEvent
}