package nah.prayer.nahdialoglib.dialog

import android.content.Context
import android.content.res.Resources
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nah.prayer.nahdialoglib.R
import nah.prayer.nahdialoglib.adapter.DialogListAdapter
import nah.prayer.recyclertoollibrary.RecyclerItemClickListener
import nah.prayer.recyclertoollibrary.WrapContentLinearLayoutManager

import java.util.ArrayList


/**
 * Created by S on 2019-04-02.
 */

class NahListDialog(context: Context, private val itemList: ArrayList<String>) : BaseDialog(context) {

    private var mTitle: String? = null
    private var mLeftText: String? = null
    private var mRightText: String? = null
    private var lastPosition = -1
    private var lastView: View? = null
    private var mMulti = false
    private val positions = ArrayList<Int>()

    override val layoutResource: Int
        get() = R.layout.dialog_lsit

    private var selectListener: SelectListener? = null


    private var multiSelectListener: MultiSelectListener? = null


    override fun setLayout() {
        val mTitleView = findViewById<TextView>(R.id.title)
        val mLeftButton = findViewById<TextView>(R.id.left)
        val mRightButton = findViewById<TextView>(R.id.right)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.adapter = DialogListAdapter(context, itemList)
        val layoutManager = WrapContentLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                context,
                recyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, i: Int) {

                        if (mMulti) {
                            if (view.isSelected) {
                                view.isSelected = false
                                positions.removeAt(i)
                            } else {
                                view.isSelected = true
                                positions.add(i)
                            }
                        } else {
                            if (lastView == null) {
                                lastView = view
                                lastView!!.isSelected = true
                            } else {
                                lastView!!.isSelected = false
                                view.isSelected = true
                                lastView = view
                            }
                        }
                        lastPosition = i

                    }

                    override fun onLongItemClick(view: View, i: Int) {

                    }
                })
        )
        if (!TextUtils.isEmpty(mTitle))
            mTitleView.text = mTitle
        if (!TextUtils.isEmpty(mLeftText))
            mLeftButton.text = mLeftText
        if (!TextUtils.isEmpty(mRightText))
            mRightButton.text = mRightText

        /*
        new Handler().postDelayed(()->{
            recyclerView.findViewHolderForAdapterPosition(0).itemView.performClick();
        },1000);*/

        mLeftButton.setOnClickListener { dismiss() }
        mRightButton.setOnClickListener {
            if (lastPosition >= 0) {
                if (mMulti) {
                    if (multiSelectListener != null) {
                        multiSelectListener!!.onPositions(positions)
                    }
                } else {
                    if (selectListener != null) {
                        selectListener!!.onPosition(lastPosition)
                    }
                }
                dismiss()
            } else {
                //appInstance.ToastMessage(getContext().getString(R.string.msg_no_selected));
            }


        }
    }

    interface SelectListener {
        fun onPosition(position: Int)
    }

    fun setOnSelectPosition(listener: (Int) -> Unit): NahListDialog {
        this.selectListener = object : SelectListener {
            override fun onPosition(position: Int) {
                listener(position)
            }
        }
        return this
    }


    interface MultiSelectListener {
        fun onPositions(list: ArrayList<Int>)
    }

    fun setOnMultiSelectPosition(listener: (ArrayList<Int>) -> Unit) : NahListDialog {
        this.multiSelectListener = object : MultiSelectListener {
            override fun onPositions(list: ArrayList<Int>) {
                listener(list)
            }
        }
        return this
    }

    fun setHeaderTitle(title: Any): NahListDialog {
        try {
            if (title is Int) {
                mTitle = context.getString(title)
            } else if (title is String) {
                mTitle = title.toString()
            }
        } catch (e: Resources.NotFoundException) {
            e.printStackTrace()
        }

        return this
    }

    fun setMultiChoice(bool: Boolean): NahListDialog {
        mMulti = bool
        return this
    }

    fun setBtnText(text: Any): NahListDialog {
        if (text is Int) {
            mRightText = context.getString(text)
        } else if (text is String) {
            mRightText = text.toString()
        }

        return this

    }

    fun setBtnText(left: Any, right: Any): NahListDialog {

        if (left is Int) {
            mLeftText = context.getString(left)
        } else if (left is String) {
            mLeftText = left.toString()
        }

        if (right is Int) {
            mRightText = context.getString(right)
        } else if (right is String) {
            mRightText = right.toString()
        }
        return this
    }


}
