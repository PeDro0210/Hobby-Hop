package com.pedro0210.hobbylobby.presentation.event

import com.pedro0210.hobbylobby.presentation.model.SocialMedia

interface UserEvent {
    data class onLoadUser(val ID: Int): UserEvent
}