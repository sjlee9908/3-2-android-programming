package com.example.final_project.ui.businesscardlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.R
import com.example.final_project.data.db.entity.BusinessCardEntity
import kotlinx.android.synthetic.main.businesscard_item.view.*

//어댑터 클래스
class BusinessCardListAdapter(
    private val businessCards : List<BusinessCardEntity>
) : RecyclerView.Adapter<BusinessCardListAdapter.BusinessCardListViewHolder>() {

    var onItemClick: ((entity: BusinessCardEntity) -> Unit)? = null
    
    //뷰홀더 만들기
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessCardListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.businesscard_item, parent, false)

        return BusinessCardListViewHolder(view)
    }

    //뷰홀더에 묶기
    override fun onBindViewHolder(holder: BusinessCardListViewHolder, position: Int) {
        holder.bindView(businessCards[position])
    }

    //명함의 개수
    override fun getItemCount() = businessCards.size

    //뷰홀더 클래스
    inner class BusinessCardListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textViewUserName: TextView = itemView.text_user_name
        private val textViewUserEmail: TextView = itemView.text_user_email
        private val textViewUserCompany: TextView = itemView.text_user_company

        private val textViewUserPosition: TextView = itemView.text_user_position
        private val textViewUserPhoneNumber: TextView = itemView.text_user_phoneNumber
        
        //뷰홀더에 묶기
        fun bindView(user: BusinessCardEntity) {
            textViewUserName.text = user.name
            textViewUserEmail.text = user.email
            textViewUserCompany.text = user.company

            textViewUserPosition.text = user.position
            textViewUserPhoneNumber.text = user.phoneNumber

            //하나의 항목이 클릭되었을 때, 리스너 등록
            //user(businessCard)를 매개변수로 한 onItemClick 실행
            itemView.setOnClickListener {
                onItemClick?.invoke(user)
            }

        }

    }
}