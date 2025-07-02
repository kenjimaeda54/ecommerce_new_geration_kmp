//
//  HomeState.swift
//  iosEcommerce
//
//  Created by kenjimaeda on 30/06/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor
class HomeState: ObservableObject {
    @Published var loadingState: LoadingState = .none
    var products: [Product] = []
    private let homeViewModel = HomeViewModel()

    func getAllProducts() async {
        loadingState = .loading

        for await result in homeViewModel.products {
            if let data = result.data as? [Product] {
                products = data
                loadingState = .success
            }

            if result.exception != nil {
                loadingState = .failure
            }

        }

    }

}
