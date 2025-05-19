//
//  SourcesScreen.swift
//  iosApp
//
//  Created by John Guerrero on 5/19/25.
//  Copyright © 2025 orgName. All rights reserved.
//
import SwiftUI
import shared

extension SourcesScreen {
    @MainActor
    class SourcesViewModelWrapper: ObservableObject {
        let sourcesViewModel: SourcesViewModel

        init() {
            sourcesViewModel = SourcesInjector().sourcesViewModel
            sourcesState = sourcesViewModel.state.value
        }

        @Published var sourcesState: SourcesState

        func startObserving() {
            Task {
                for await sourcesS in sourcesViewModel.state {
                    self.sourcesState = sourcesS
                }
            }
        }
    }
}

struct SourcesScreen: View {
    @Environment(\.dismiss)
    private var dismiss
    @ObservedObject private(set) var viewModel: SourcesViewModelWrapper

    var body: some View {
        NavigationStack {

            VStack {
                if viewModel.sourcesState.loading {
                    Loader()
                }

                if let error = viewModel.sourcesState.error {
                    ErrorMessage(message: error)
                }

                if !viewModel.sourcesState.sources.isEmpty {
                    ScrollView {
                        LazyVStack(spacing: 10) {
                            ForEach(
                                viewModel.sourcesState.sources,
                                id: \.self
                            ) { source in
                                SourceItemView(
                                    source: source
                                )
                            }
                        }
                    }
                }
            }
            .onAppear {
                self.viewModel.startObserving()
            }
            .navigationTitle("Sources")
            .toolbar {
                ToolbarItem(placement: .primaryAction) {
                    Button {
                        dismiss()
                    } label: {
                        Text("Done")
                            .bold()
                    }
                }
            }
        }
    }
}

struct SourceItemView: View {
    var source: Source

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Spacer()
                .frame(height: 8)
            Text(source.name)
                .font(.headline)
                .padding(.vertical, 4)
            Spacer()
                .frame(height: 8)
            Text(source.description)
                .font(.subheadline)
                .foregroundColor(.gray)
            Spacer()
                .frame(height: 8)
            Text(source.locale)
                .font(.caption)
                .foregroundColor(.gray)
        }
        .padding()
        .background(Color.white)
        .cornerRadius(8)
        .shadow(radius: 4)
    }
}
