//
//  DetailScreen.swift
//  iosApp
//
//  Created by Joseph on 9/6/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

@available(iOS 15.0, *)
struct DetailScreen: View {
    let movie:Movie
    
    var body: some View {
        ScrollView{
            VStack{
                ZStack{
                        AsyncImage(url: URL(string: movie.posterImageUrl)){image in
                            image.resizable().scaledToFill()
                        }placeholder: {
                            ProgressView()
                        }
        
                }.frame(maxWidth: .infinity,minHeight: 300,maxHeight: 300)
                
                VStack(alignment:.leading,spacing: 12){
                    
                    Text(movie.title)
                        .font(.title)
                        .fontWeight(.bold)
                        .fixedSize(horizontal: false, vertical: true)
                    
                    Button(action: {
                        
                    }){
                        
                        HStack{
                            Image(systemName: "play.fill")
                                .foregroundColor(.black)
                            
                            Text("Start wathing now")
                           .foregroundColor(.black)
                            
                        }
                    }.frame(maxWidth: .infinity, maxHeight: 40)
                        .padding(EdgeInsets(top: 20, leading: 8, bottom:20, trailing: 8))
                        .background(.red)
                        .clipShape(RoundedRectangle(cornerRadius: 8))
            
                    Text("Relese in \(movie.releaseDate)".uppercased())
                    
                    Text(movie.overview)
                        .font(.body)
                        .fixedSize(horizontal: false, vertical: true)
                                                
                }.padding(20)
                    .background(.black)
                
            }.frame(maxWidth: .infinity,maxHeight: .infinity)
        }
    }
}


struct DetailScreen_Previews: PreviewProvider {
    static var previews: some View {
        if #available(iOS 15.0, *) {
            DetailScreen(movie: sampleMovie)
        } else {
            // Fallback on earlier versions
        }
    }
}
