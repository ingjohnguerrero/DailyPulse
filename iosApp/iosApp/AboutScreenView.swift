//
//  AboutScreenView.swift
//  iosApp
//
//  Created by John Guerrero on 10/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct AboutScreenView: View {
    var body: some View {
        NavigationStack {
            AboutListView()
                .navigationTitle("About Device")
        }
    }
}

#Preview {
    AboutScreenView()
}
