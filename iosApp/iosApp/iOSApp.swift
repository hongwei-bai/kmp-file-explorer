import UIKit
import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        KoinKt.doInitKoinIos()
//        NativeGreeter.shared.delegate = { name in
//            SwiftGreeter.greet(withName: name)
//        }
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
