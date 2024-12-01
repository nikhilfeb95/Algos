import unittest

class TestGetMinErrors(unittest.TestCase):
    def test_get_min_errors(self):
        # Test cases in format: (input_string, x, y, expected_output)
        test_cases = [
            ("101!1", 2, 3, 6),  # The example from your print statement
            ("!!", 1, 1, 0),     # All combinations result in 0 errors
            ("10!", 2, 2, 2),    # Best option is "100" or "101"
            ("!0!", 3, 2, 2),    # Best option is "001"
            ("1111", 2, 3, 0),   # No exclamation marks, no choices needed
        ]
        
        for test_string, x, y, expected in test_cases:
            with self.subTest(test_string=test_string, x=x, y=y):
                result = getMinErrors(test_string, x, y)
                self.assertEqual(result, expected, 
                    f"Failed for string={test_string}, x={x}, y={y}. "
                    f"Expected {expected}, but got {result}")

if __name__ == '__main__':
    unittest.main()

            