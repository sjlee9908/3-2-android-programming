package com.example.final_project.ui.businesscardlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.final_project.R
import com.example.final_project.data.db.AppDatabase
import com.example.final_project.data.db.dao.BusinessCardDao
import com.example.final_project.repository.DatabaseDataSource
import com.example.final_project.repository.BusinessCardRepository
import kotlinx.android.synthetic.main.businesscard_list_fragment.*

//프래그먼트 제작
class BusinessCardListFragment : Fragment(R.layout.businesscard_list_fragment) {
    //room을 사용하기 위한 뷰모델 로드
    private val viewModel: BusinessCardListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val businessCardDao: BusinessCardDao =
                    AppDatabase.getInstance(requireContext()).businessCardDao

                val repository: BusinessCardRepository = DatabaseDataSource(businessCardDao)
                return BusinessCardListViewModel(repository) as T
            }
        }
    }
    
    //뷰 생성 후, livedata 변경시 함수 호출
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModelEvents()
        configureViewListeners()
    }

    //livedata변경 시 호출되는 함수, 어댑터 등록
    private fun observeViewModelEvents() {
        //livedata 변경시 호출
        //BusinessCardListAdapter를 만든 뒤, apply함수 실행
        //apply함수 내부에서는 onItemclick를 초기화하고 끝
        viewModel.allBusinessCards.observe(viewLifecycleOwner) { allBusinessCards ->
            val businessCardListAdapter = BusinessCardListAdapter(allBusinessCards).apply {
                //businessCard 전달
                onItemClick = { businessCard ->
                    val directions = BusinessCardListFragmentDirections
                        .actionUserListFragmentToUserFragment(businessCard)
                    findNavController().navigate(directions)
                }
            }

            //리사이클러 뷰 지정
            with(recycler_businessCards) {
                setHasFixedSize(true)
                adapter = businessCardListAdapter  //어댑터 지정
            }
        }

    }

    //onresume 함수
    override fun onResume() {
        super.onResume()
        viewModel.getBusinessCards()
    }

    //명함 추가 프래그먼트로 이동
    private fun configureViewListeners() {
        fabAddUser.setOnClickListener {
            findNavController().navigate(
                R.id.action_userListFragment_to_userFragment
            )
        }
    }

}