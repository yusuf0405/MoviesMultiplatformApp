//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Joseph on 9/6/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension HomeScreen {
    
   @MainActor class HomeViewModel: ObservableObject {
       
       private let fetchAllMoviesUseCase = FetchAllMoviesUseCase.init()
       
       @Published private(set) var movies:[Movie] = []
       @Published private(set) var isLoading:Bool = false
       @Published private(set) var loadFinished:Bool = false
       
       private var cureentPage = 1
       
       func loadMovies() async {
           if(isLoading) {
               return
           }
        
           do {
               let movies = try await fetchAllMoviesUseCase.invoke(page: Int32(cureentPage))
               isLoading = false
               loadFinished = movies.isEmpty
               
               cureentPage += 1
               
               self.movies = self.movies + movies
               
           } catch {
               isLoading = false
               loadFinished = true
               print(error.localizedDescription)
           }
       }
    }
}

