import re

if __name__ == "__main__":
    response = [
        "X1 ürününden sipariş ver"
        , "Kafana göre takıl"
        , "X1 X2 ürününden sipariş ver"
        , "X1 ve X2 ürünüden sipariş ver"
        , "X1 X2 X3 ürününden sipariş ver"]

    pattern = r'\bX\S*\b'

    matches = []

    for item in response:

        x_matches = re.findall(pattern, item)
        if x_matches:
            matches.extend(x_matches)

    print(matches)


