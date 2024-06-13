package com.example.final_project.ui.businesscard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.final_project.R
import com.example.final_project.data.db.AppDatabase
import com.example.final_project.data.db.dao.BusinessCardDao
import com.example.final_project.repository.DatabaseDataSource
import com.example.final_project.repository.BusinessCardRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.businesscard_fragment.*

//명함 수정 프레그먼트
class BusinessCardFragment : Fragment(R.layout.businesscard_fragment) {
    //room을 사용하기 위한 뷰모델 로드
    //제네릭을 통한 생성
    private val viewModel: BusinessCardViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val businessCardDao: BusinessCardDao =
                    AppDatabase.getInstance(requireContext()).businessCardDao

                val repository: BusinessCardRepository = DatabaseDataSource(businessCardDao)
                return BusinessCardViewModel(repository) as T  
            }
        }
    }

    //명함 리사이클러 뷰로부터 전달받은 데이터
    private val args: BusinessCardFragmentArgs by navArgs()

    //뷰 생성되고 텍스트 뷰에 지정
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //넘겨받은 user에 따라 input의 텍스트를 지정함
        args.user?.let { user ->
            //항목에 문자열 지정
            input_name.setText(user.name)
            input_email.setText(user.email)
            input_company.setText(user.company)
            input_position.setText(user.position)
            input_phoneNumber.setText(user.phoneNumber)
            //버튼 텍스트를 edit로 
            btn_user.text = getString(R.string.user_update_button_text)
            //삭제 버튼 활성화
            btn_delete.visibility = View.VISIBLE  
        }


        observeEvents()
        setListeners()
    }

    //livedata가 변경
    private fun observeEvents() {
        //livedata가 변경될 때마다 clearField(), 포커싱 요구, 뒤로가기 수행
        viewModel.businessCardStateEventData.observe(viewLifecycleOwner) { userState ->
            when(userState) {
                is BusinessCardViewModel.BusinessCardState.Inserted,
                is BusinessCardViewModel.BusinessCardState.Updated,
                is BusinessCardViewModel.BusinessCardState.Deleted -> {
                    clearFields()
                    requireView().requestFocus()

                    findNavController().popBackStack()
                }
            }
        }

        //livedata가 변경될 때마다 스낵바로 보여줌
        viewModel.messageEventData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }

    }

    //입력창 비우기
    private fun clearFields() {
        input_name.text?.clear()
        input_email.text?.clear()
        input_company.text?.clear()
        input_position.text?.clear()
        input_phoneNumber.text?.clear()
    }

    //버튼에 클릭 리스너 등록
    private fun setListeners() {
        btn_user.setOnClickListener {
            val name = input_name.text.toString()
            val email = input_email.text.toString()
            val company = input_company.text.toString()
            val position = input_position.text.toString()
            val phoneNumber = input_phoneNumber.text.toString()

            viewModel.addOrUpdateBusinessCard(name, email, company, position, phoneNumber, args.user?.id ?: 0)
        }

        btn_delete.setOnClickListener {
            viewModel.removeBusinessCard(args.user?.id ?: 0)
        }
    }

}