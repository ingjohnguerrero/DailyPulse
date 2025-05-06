//
//  ArticlesScreen.swift
//  iosApp
//
//  Created by John Guerrero on 4/22/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

extension ArticlesScreen {
    @MainActor
    class ArticlesViewModelWrapper: ObservableObject {
        let articlesViewModel: ArticlesViewModel


        init() {
            articlesViewModel = ArticlesInjector().articlesViewModel
            articlesState = articlesViewModel.articlesState.value
        }

        @Published var articlesState: ArticlesState

        func startObserving() {
            Task {
                for await articlesS in articlesViewModel.articlesState {
                    self.articlesState = articlesS
                }
            }
        }
    }
}

struct ArticlesScreen: View {

    @ObservedObject private(set) var viewModel: ArticlesViewModelWrapper

    var body: some View {
        VStack {
            AppBar()

            if viewModel.articlesState.loading {
                Loader()
            }

            if let error = viewModel.articlesState.error {
                ErrorMessage(message: error)
            }

            if !viewModel.articlesState.articles.isEmpty {
                ScrollView {
                    LazyVStack(spacing: 10) {
                        ForEach(viewModel.articlesState.articles, id: \.self) { article in
                            ArticleImageView(article: article)
                        }
                    }
                }
            }
        }
        .onAppear {
            self.viewModel.startObserving()
        }
    }
}

struct AppBar: View {
    var body: some View {
        Text("Articles")
            .font(.largeTitle)
            .fontWeight(.bold)
    }
}

struct ArticleImageView: View {

    var article: Article

    var body: some View {
        VStack(
            alignment: .leading, spacing: 8
        ){
            AsyncImage(url: URL(string: article.imageUrl)) { phase in
                if phase.image != nil {
                    phase.image!
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Error loading image")
                } else {
                    ProgressView()
                }
            }
            Text(article.title)
                .font(.title)
                .fontWeight(.bold)
            Text(article.desc)
            Text(article.date)
                .frame(maxWidth: .infinity, alignment: .trailing)
                .foregroundStyle(.gray)
        }
        .padding(16)
    }
}

struct Loader: View {
    var body: some View {
        ProgressView()
    }
}

struct ErrorMessage: View {
    var message: String
    var body: some View {
        Text(message)
            .font(.title)
    }
}
