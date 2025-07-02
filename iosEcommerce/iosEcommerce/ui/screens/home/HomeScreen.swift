//
//  HomeScreen.swift
//  iosEcommerce
//
//  Created by kenjimaeda on 30/06/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct HomeScreen: View {
    @StateObject private var homeState = HomeState()
    
    var body: some View {
        ScrollView {
        if(homeState.loadingState == .success) {
            LazyVStack {
                ForEach(homeState.products,id:\.id) { product in
                    Text(product.title)
                }
            }
        }
            
     }
    .task {
            await homeState.getAllProducts()
        }
        
    }
        
}

#Preview {
    HomeScreen()
}
