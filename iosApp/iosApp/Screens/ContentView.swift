import SwiftUI
import shared

struct ContentView: View {
    @State private var shouldOpenAbout = false
    @State private var shouldOpenSources = false

    var body: some View {
        let articlesScreen = ArticlesScreen(viewModel: .init())
        NavigationStack {
            articlesScreen
                .toolbar {
                    Button {
                        shouldOpenAbout = true
                    } label: {
                        Label("About", systemImage: "info.circle")
                            .labelStyle(.titleAndIcon)
                    }
                    .popover(isPresented: $shouldOpenAbout) {
                        AboutScreenView()
                    }
                    Button {
                        shouldOpenSources = true
                    } label: {
                        Label("Sources", systemImage: "list.bullet.rectangle")
                            .labelStyle(.titleAndIcon)
                    }.popover(isPresented: $shouldOpenSources) {
                        SourcesScreen(viewModel: .init())
                    }
                }
        }.refreshable {
            articlesScreen.viewModel.articlesViewModel.getArticles(forceFetch: true)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
