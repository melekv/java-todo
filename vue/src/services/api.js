const API_URL = import.meta.env.VITE_API_URL;

const getUsers = async () => {
    const response = await fetch(`${API_URL}/users`);

    if (!response.ok) {
        throw new Error('Failed to fetch users!');
    }

    return response.json();
}

const getUser = async (id) => {
    const response = await fetch(`${API_URL}/users/${id}`);

    if (!response.ok) {
        throw new Error('Failed to fetch user!');
    }

    return response.json();
};

const createUser = async (data) => {
    const response = await fetch(`${API_URL}/users`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
        body: JSON.stringify({
            firstName: data.firstName,
            lastName: data.lastName,
            email: data.email,
        })
    });

    if (!response.ok) {
        throw new Error('Error creating user!');
    }
}

const updateUser = async (data) => {
    const response = await fetch(`${API_URL}/users/${data.id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
        body: JSON.stringify({
            firstName: data.firstName,
            lastName: data.lastName,
            active: data.active,
        })
    });

    if (!response.ok) {
        throw new Error('Error creating todo!');
    }
}

const deleteUser = async (id) => {
    const response = await fetch(`${API_URL}/users/${id}`, {
        method: 'DELETE',
    });

    if (!response.ok) {
        throw new Error(`HTTP error: ${response.status}`);
    }
}

const getCategories = async () => {
    const response = await fetch(`${API_URL}/categories`);

    if (!response.ok) {
        throw new Error('Failed to fetch categories!');
    }

    return response.json();
}

const createCategory = async (name) => {
    const response = await fetch(`${API_URL}/categories`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
        body: JSON.stringify({
            name: name,
        })
    });

    if (!response.ok) {
        throw new Error('Error creating category!');
    }
}

const deleteCategory = async (id) => {
    const response = await fetch(`${API_URL}/categories/${id}`, {
        method: 'DELETE',
    });

    if (!response.ok) {
        throw new Error(`HTTP error: ${response.status}`);
    }
}

const getTodos = async () => {
    const response = await fetch(`${API_URL}/todos`);

    if (!response.ok) {
        throw new Error('Failed to fetch todos!');
    }

    return response.json();
};

const getTodo = async (id) => {
    const response = await fetch(`${API_URL}/todos/${id}`);

    if (!response.ok) {
        throw new Error('Failed to fetch todo!');
    }

    return response.json();
};

const createTodo = async (data) => {
    const response = await fetch(`${API_URL}/todos`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
        body: JSON.stringify({
            title: data.title,
            description: data.description,
            userId: data.userId,
            categoryId: data.categoryId,
        })
    });

    if (!response.ok) {
        throw new Error('Error creating todo!');
    }
}

const updateTodo = async (data) => {
    const response = await fetch(`${API_URL}/todos/${data.id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
        body: JSON.stringify({
            title: data.title,
            description: data.description,
            status: data.status,
            categoryId: data.categoryId,
        })
    });

    if (!response.ok) {
        throw new Error('Error creating todo!');
    }
}

const deleteTodo = async (id) => {
    const response = await fetch(`${API_URL}/todos/${id}`, {
        method: 'DELETE',
    });

    if (!response.ok) {
        throw new Error(`HTTP error: ${response.status}`);
    }
}

export { getUsers, getUser, createUser, updateUser, deleteUser, getCategories, createCategory, deleteCategory,
    getTodos, getTodo, createTodo, updateTodo, deleteTodo };
