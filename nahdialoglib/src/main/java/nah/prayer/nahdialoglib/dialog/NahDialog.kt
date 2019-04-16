package nah.prayer.nahdialoglib.dialog

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.widget.TextView
import nah.prayer.nahdialoglib.R


/**
 * Created by S on 2019-04-02.
 */
class NahDialog(context: Context) : BaseDialog(context) {

    private var mTitle: String? = null
    private var mContent: String? = null
    private var mLeftText: String? = null
    private var mRightText: String? = null
    private var isContent = true

    private var mLeftClickListener: View.OnClickListener? = null
    private var mRightClickListener: View.OnClickListener? = null


    override val layoutResource: Int
        get() = R.layout.dialog_nah

    override fun setLayout() {
        val mTitleView = findViewById<TextView>(R.id.title)
        val mContentView = findViewById<TextView>(R.id.str)
        val mLeftButton = findViewById<TextView>(R.id.left)
        val mRightButton = findViewById<TextView>(R.id.right)

        mTitleView.text = mTitle
        mLeftButton.text = mLeftText
        mRightButton.text = mRightText

        if (isContent) {
            mContentView.text = mContent
        } else {
            mContentView.visibility = View.GONE
        }

        // 클릭 이벤트 셋팅
        if (mLeftClickListener != null && mRightClickListener != null) {
            mLeftButton.setOnClickListener(mLeftClickListener)
            mRightButton.setOnClickListener(mRightClickListener)
        } else if (mRightClickListener != null && mLeftClickListener == null) {
            mRightButton.setOnClickListener(mRightClickListener)
            mLeftButton.visibility = View.GONE
        }
    }


    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.
    fun setDialog(title: Any, content: Any, singleListener: View.OnClickListener): NahDialog {
        CheckText(title, content)
        this.mRightClickListener = singleListener
        return this
    }

    // 클릭버튼이 확인과 취소 두개일때 생성자 함수로 이벤트를 받는다
    fun setDialog(
        title: Any,
        content: Any,
        leftListener: View.OnClickListener,
        rightListener: View.OnClickListener
    ): NahDialog {
        CheckText(title, content)
        this.mLeftClickListener = leftListener
        this.mRightClickListener = rightListener
        return this
    }

    private fun CheckText(title: Any?, content: Any?) {
        try {
            if (title is Int) {
                mTitle = context.getString(title)
            } else if (title is String) {
                mTitle = title.toString()
            }

            if (content != null) {
                if (content is Int) {
                    mContent = context.getString(content)
                } else if (content is String) {
                    mContent = content.toString()
                }
            } else {
                isContent = false
            }
        } catch (e: Resources.NotFoundException) {
            e.printStackTrace()
        }

    }


    fun setBtnText(text: Any): NahDialog {
        if (text is Int) {
            mRightText = context.getString(text)
        } else if (text is String) {
            mRightText = text.toString()
        }

        return this

    }

    fun setBtnText(left: Any, right: Any): NahDialog {

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

    fun setCancel(bool: Boolean): NahDialog {
        setCancelable(bool)
        return this
    }


}
