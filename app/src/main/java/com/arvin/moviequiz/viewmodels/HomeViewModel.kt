package com.arvin.moviequiz.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arvin.moviequiz.api.ApiHandler
import com.arvin.moviequiz.models.DataMovies
import com.arvin.moviequiz.models.Movies
import com.arvin.moviequiz.repositories.HomeRepository
import com.arvin.moviequiz.util.Const

class HomeViewModel: ViewModel() {
    private val homeRepository = HomeRepository()
    private var _manageData:MutableLiveData<Movies> = MutableLiveData<Movies>()
    private var _checkAnswer:MutableLiveData<Pair<Int,Int>> = MutableLiveData<Pair<Int,Int>>()
    private var _setRightAnswerCount:MutableLiveData<String> = MutableLiveData<String>()
    private var _setWrongAnswerCount:MutableLiveData<String> = MutableLiveData<String>()
    private var rightAnswerCount:Int = 0
    private var wrongAnswerCount:Int = 0
    var resultImageUrl:ObservableField<String> = ObservableField<String>()
    var loadingQuestion:ObservableField<Boolean>  = ObservableField<Boolean>()
    var btnAnswerVisibility:ObservableField<Boolean> = ObservableField<Boolean>()

    init{
        resultImageUrl.set("")
        btnAnswerVisibility.set(false)
    }

    val setRightAnswerCount:LiveData<String>
        get() = _setRightAnswerCount
    val setWrongAnswerCount:LiveData<String>
        get() = _setWrongAnswerCount
    val manageData:LiveData<Movies?>
        get() = _manageData
    val checkAnswer: LiveData<Pair<Int, Int>>
        get() = _checkAnswer

    fun setValueCheckAnswer(userGuess:Int){
        val rightAnswer: Int = _manageData.value!!.correctAnswerIdx
        if(userGuess == rightAnswer){
            _checkAnswer.value = Pair(1,rightAnswer)
        }
        else{
            _checkAnswer.value = Pair(0,rightAnswer)
        }
    }
    fun getMovies(): LiveData<Movies?>? {
            loadingQuestion.set(true)
            var randomAnswerList:ArrayList<DataMovies> = ArrayList<DataMovies>()
            homeRepository.getMovies(object:ApiHandler {
                override fun OnResponse(response: Movies?) {
                    var sizeData:Int = response?.dataMovies?.size ?: 0
                    for(i in 0..3){
                        var randomIdx = (Math.random()*sizeData).toInt()
                        var randomAnswer: DataMovies = response?.dataMovies!!.get(randomIdx)
                        randomAnswerList.add(randomAnswer)
                    }
                    var movies = Movies()
                    movies.dataMovies = randomAnswerList
                    movies.correctAnswerIdx = (Math.random()*randomAnswerList.size).toInt()
                    _manageData.value = movies
                    resultImageUrl.set(Const.BASE_URL_IMAGE+randomAnswerList.get(movies.correctAnswerIdx).posterMovie)
                    btnAnswerVisibility.set(true)
                    loadingQuestion.set(false)
                }
            })
            return _manageData
    }

    fun setRightOrWrongAnswerCount(result:Int){
        if(result == 1){
            rightAnswerCount++
        }
        else if(result == 0){
            wrongAnswerCount++
        }
        _setRightAnswerCount.value = "Right Answer Count : $rightAnswerCount"
        _setWrongAnswerCount.value = "Wrong Answer Count : $wrongAnswerCount"
    }

}