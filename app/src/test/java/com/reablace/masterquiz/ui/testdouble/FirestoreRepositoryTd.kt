package com.reablace.masterquiz.ui.testdouble

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QuerySnapshot
import com.reablace.masterquiz.firebase.firestore.FirestoreRepositoryContract

class FirestoreRepositoryTd : FirestoreRepositoryContract {
    override suspend fun getUserTenancy(userAuthId: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getTenancyEventCollection(userTenancyId: String): QuerySnapshot {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPlayersCollection(): CollectionReference? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getQuestionsCollection(): CollectionReference? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getQuizzesCollection(): CollectionReference? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTenanciesCollection(): CollectionReference? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUsersTenanciesCollection(): CollectionReference? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
