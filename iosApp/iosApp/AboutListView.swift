//
//  AboutListView.swift
//  iosApp
//
//  Created by John Guerrero on 10/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AboutListView: View {
    private struct RowIem: Hashable {
        let title: String
        let subtitle: String
    }

    private let items: [RowIem] = {
        let platform = Platform()
        platform.logSystemInfo()

        var results: [RowIem] = [
            .init(title: "Operating System", subtitle: "\(platform.osName) \(platform.osVersion)"),
            .init(title: "Davice", subtitle: platform.deviceModel),
            .init(title: "Density", subtitle: "@\(platform.density)x")

        ]
        return results
    }()

    var body: some View {
        List(items, id: \.self) { item in
            VStack(alignment: .leading) {
                Text(item.title)
                    .font(.footnote)
                    .foregroundStyle(.secondary)
                Text(item.subtitle)
                    .font(.body)
                    .foregroundStyle(.primary)
            }
            .padding(.vertical, 4)
        }
    }
}

#Preview {
    AboutListView()
}
