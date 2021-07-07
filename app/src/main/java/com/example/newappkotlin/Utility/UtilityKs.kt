package com.example.newappkotlin.Utility

import android.view.View
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout

class UtilityKs {

    companion object {

        fun startShimmer(shimmer: ShimmerFrameLayout) {
            shimmer.visibility = View.VISIBLE
            shimmer.startShimmer()
        }

        fun stopShimmer(shimmer: ShimmerFrameLayout) {
            shimmer.visibility = View.GONE
            shimmer.stopShimmer()
        }
    }
}