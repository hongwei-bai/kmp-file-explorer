import SwiftUI

@main
struct iOSApp: App {
    let factory = Factory()
    var body: some Scene {
        WindowGroup {
            ContentView(factory)
        }
    }
}