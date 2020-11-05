package com.example.mytriviya.di


import com.example.mytriviya.Adapter
import com.example.mytriviya.TriviaViewModel
import com.example.mytriviya.data.Repository
import com.example.mytriviya.data.local.TriviyaDataBase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
//Dipendency Injection module declaring
val appModule = module {

    viewModel { TriviaViewModel(get()) }

    //single : Only one instance of this class will be created  and injected evrywher
    single { TriviyaDataBase.getDbInstance(get()) }
    single { Adapter() }
}
val repositoryModule = module {
    factory { Repository(get()) }
}

