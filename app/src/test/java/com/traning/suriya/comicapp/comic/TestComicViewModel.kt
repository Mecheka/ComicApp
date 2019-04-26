package com.traning.suriya.comicapp.comic

import com.traning.suriya.comicapp.base.BaseTest
import com.traning.suriya.comicapp.repository.MainRepository
import com.traning.suriya.comicapp.scene.comic.MainViewModel
import org.junit.Before
import org.koin.test.inject

class TestComicViewModel: BaseTest() {

    private val repository: MainRepository by inject()
    private val viewModel: MainViewModel by viewModel{}

    @Before
    override fun setUp() {
        super.setUp()
    }
}