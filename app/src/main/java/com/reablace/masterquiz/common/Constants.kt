package com.reablace.masterquiz.common

import com.reablace.masterquiz.BuildConfig

//const val DATA_SHARED_PREFERENCES: String = "data"

// region User sesion SharedPrefs
const val USER_SESION_SHARED_PREFERENCES: String = "user_sesion_data"
const val LOGIN_USER_NAME: String = "user_name"
const val USER_SESION_AUTH_ID: String = "user_aut_id"
const val USER_SESION_TENANT_ID: String = "user_tenant_id"
// endregion

// region Data SharedPrefs
const val DATA_SHARED_PREFERENCES: String = "data"
// endregion

// region Firebase
const val ROOT: String = "root/${BuildConfig.FIRESTORE_FLAVOR}"
const val PLAYERS_ROOT: String = "${ROOT}/players"
const val QUESTIONS_ROOT: String = "${ROOT}/questions"
const val QUIZZES_ROOT: String = "${ROOT}/quizzes"

// Tenancies node
const val TENANCIES_ROOT: String = "${ROOT}/tenancies"

// Events node
const val EVENTS_ROOTS: String = "${ROOT}/events"


// Users Tenancies node
const val USERS_TENANCIES_ROOT: String = "${ROOT}/userstenancies"
const val USERS_TENANCIES_FIELD_ID: String = "tenancyId"

const val FIREBASE_TIMEOUT: Long = 5_000
// endregion

